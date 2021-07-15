package qlks_hdv.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import qlks_hdv.entity.BookingCard;
import qlks_hdv.entity.BookingDetail;
import qlks_hdv.entity.Customer;
import qlks_hdv.entity.Discount;
import qlks_hdv.entity.RentingDetail;
import qlks_hdv.entity.Room;
import qlks_hdv.entity.RoomStatus;
import qlks_hdv.entity.RoomType;
import qlks_hdv.entity.ServiceDetail;
import qlks_hdv.entity.Staff;
import qlks_hdv.exception.BadRequestException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.mapper.BookingCardMapper;
import qlks_hdv.mapper.BookingDetailMapper;
import qlks_hdv.mapper.ServiceDetailMapper;
import qlks_hdv.repository.BookingCardRepository;
import qlks_hdv.repository.BookingDetailRepository;
import qlks_hdv.repository.CustomerRepository;
import qlks_hdv.repository.DiscountRepository;
import qlks_hdv.repository.RentingDetailRepository;
import qlks_hdv.repository.RoomRepostiory;
import qlks_hdv.repository.RoomTypeRepository;
import qlks_hdv.repository.ServiceDetailRepository;
import qlks_hdv.repository.StaffRepository;
import qlks_hdv.request.CreateBookingCardRequest;
import qlks_hdv.response.GetBookingCardForPaymentReponse;
import qlks_hdv.response.GetBookingCardReponse;
import qlks_hdv.response.GetBookingDetailResponse;
import qlks_hdv.response.GetServiceDetailResponse;
import qlks_hdv.service.IBookingCardService;

@Data
@RequiredArgsConstructor
@Service
public class BookingCardService implements IBookingCardService {

  private final BookingCardRepository bookingCardRepository;
  private final DiscountRepository discountRepository;
  private final CustomerRepository customerRepository;
  private final BookingCardMapper bookingCardMapper;
  private final ServiceDetailRepository serviceDetailRepository;
  private final BookingDetailRepository bookingDetailRepository;
  private final StaffRepository staffRepository;
  private final RentingDetailRepository rentingDetailRepository;
  private final RoomRepostiory roomRepostiory;
  private final BookingDetailMapper bookingDetailMapper;
  private final RoomTypeRepository roomTypeRepository;
  private final ServiceDetailMapper serviceDetailMapper;

  @Override
  public List<GetBookingCardReponse> getAllBookingCardsInReservated() {

    List<BookingCard> bookingCardList = bookingCardRepository
        .findAllByStatus("Reservated");
    List<GetBookingCardReponse> getBookingCardReponseList = bookingCardList.stream()
        .map(develop -> bookingCardMapper.mapToGetBookingCardReponse(develop))
        .collect(Collectors.toList());
    return getBookingCardReponseList;

  }


  @Override
  @Transactional
  public void createBookingCard(CreateBookingCardRequest createBookingCardRequest) {

    Customer customer = customerRepository
        .findByUserUsername(createBookingCardRequest.getUsername())
        .orElseThrow(() -> new NotFoundException("customer-not-found"));

    Discount discount = discountRepository
        .findByDiscountName(createBookingCardRequest.getDiscountName());

    BookingCard bookingCard = bookingCardMapper
        .mapToBookingCard(createBookingCardRequest, 0, "Processing", customer, discount);

    bookingCardRepository.save(bookingCard);

  }

  @Override
  public List<GetBookingCardReponse> getAllBookingCardsByUsername(String username) {
    List<BookingCard> bookingCardList = bookingCardRepository
        .findAllByCustomerUserUsername(username);
    List<GetBookingCardReponse> getBookingCardReponseList = bookingCardList.stream()
        .map(develop -> bookingCardMapper.mapToGetBookingCardReponse(develop))
        .collect(Collectors.toList());
    return getBookingCardReponseList;
  }

  @Override
  @Transactional
  public void updatePriceOfBookingCard(int bookingId) {

    BookingCard bookingCard = bookingCardRepository.findById(bookingId)
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    List<BookingDetail> bookingDetailList = bookingDetailRepository
        .findAllByBookingCardBookingId(bookingId);

    List<ServiceDetail> serviceDetailList = serviceDetailRepository
        .findAllByBookingCardBookingId(bookingId);

    int price = 0;
    price = bookingDetailList.stream()
        .mapToInt(sla -> sla.getPrice() * sla.getAmount())
        .sum();

    price += serviceDetailList.stream()
        .mapToInt(sla -> sla.getPrice() * sla.getQuantity())
        .sum();

    bookingCard.setPrice(price);
    bookingCardRepository.save(bookingCard);

  }

  @Override
  @Transactional
  public void updateCompleteDateOnBookingCard(int bookingId) {

    BookingCard bookingCard = bookingCardRepository.findById(bookingId)
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    Sort sort = Sort.by(Direction.DESC, "backAt");
    List<BookingDetail> bookingDetailList = bookingDetailRepository
        .findAllByBookingCardBookingId(bookingId, sort);

    bookingCard.setCompleteAt(bookingDetailList.get(0).getBackAt());
    bookingCardRepository.save(bookingCard);

  }


  @Override
  @Transactional
  public void changeStatusBookingCard(Integer bookingId, Boolean isCancel) {

    BookingCard bookingCard = bookingCardRepository.findById(bookingId)
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));
    if (isCancel) {
      if (bookingCard.getStatus().equals("Completed") || bookingCard.getStatus()
          .equals("Renting")) {
        throw new BadRequestException("can-not-disable");
      }
      bookingCard.setStatus("Cancel");
    } else {
      if (bookingCard.getStatus().equals("Completed")) {
        throw new BadRequestException("can-not-change");
      }
      if (bookingCard.getStatus().equals("Processing")) {
        bookingCard.setStatus("Reservated");
      } else if (bookingCard.getStatus().equals("Reservated")) {
        bookingCard.setStatus("Renting");
      } else if (bookingCard.getStatus().equals("Renting")) {
        bookingCard.setStatus("Completed");
      }
    }
    bookingCardRepository.save(bookingCard);

  }

  @Override
  public HashMap<Integer, Integer> getRevenueAtDate(int year) {
    HashMap<Integer, Integer> revenueList = new HashMap<>();
    List<BookingCard> bookingCardList = bookingCardRepository.findAll();
    for (int i = 0; i < 12; i++) {
      revenueList.put(i, 0);
    }
    year = year - 1900;
    for (BookingCard bookingCard : bookingCardList) {
      List<Integer> tempList = getPriceCalcute(year, bookingCard);
      revenueList.put(tempList.get(0), (tempList.get(1) + revenueList.get(tempList.get(0))));
    }
    return revenueList;
  }

  @Override
  public List<Integer> getPriceCalcute(int year, BookingCard bookingCard) {
    List<Integer> priceAndMonth = new ArrayList<>();
    priceAndMonth.add(0);
    priceAndMonth.add(0);
    try {
      Date revenueAt = new SimpleDateFormat("yyyy-MM-dd").parse(bookingCard.getCompleteAt().trim());
      Calendar cal = Calendar.getInstance();
      cal.setTime(revenueAt);
      int monthTemp = cal.getTime().getMonth();
      int yearTemp = cal.getTime().getYear();
      priceAndMonth.set(0, monthTemp);
      priceAndMonth.set(1, 0);
      if (yearTemp == year) {
        priceAndMonth.set(1, bookingCard.getPrice());
      }

    } catch (ParseException e) {
    }
    return priceAndMonth;
  }

  @Override
  @Transactional
  public GetBookingCardForPaymentReponse Payment(Integer bookingId, String username,
      HttpServletResponse response) {

    BookingCard bookingCard = bookingCardRepository.findById(bookingId)
        .orElseThrow(() -> new NotFoundException("booking-card-not-found"));

    if (!bookingCard.getStatus().equals("Renting")) {
      throw new BadRequestException("status-is-not-valid");
    }

    Staff staff = staffRepository.findByUserUsername(username)
        .orElseThrow(() -> new NotFoundException("user-not-fount"));

    List<BookingDetail> bookingDetailList = bookingDetailRepository
        .findAllByBookingCardBookingId(bookingId);
    int bookingAmount = 0;
    for (BookingDetail bookingDetail : bookingDetailList) {
      bookingAmount += bookingDetail.getAmount() * bookingDetail.getPrice();
    }

    List<ServiceDetail> serviceDetailList = serviceDetailRepository
        .findAllByBookingCardBookingId(bookingId);

    int serviceAmount = 0;
    for (ServiceDetail serviceDetail : serviceDetailList) {
      serviceAmount += serviceDetail.getPrice() * serviceDetail.getQuantity();
    }
    GetBookingCardForPaymentReponse getBookingCardForPaymentReponse = bookingCardMapper
        .mapTGetBookingCardForPaymentReponse(bookingCard, serviceAmount, bookingAmount, username,
            bookingCard.getCustomer());

    List<RentingDetail> rentingDetails = rentingDetailRepository
        .findAllByBookingCardBookingId(bookingId);

    for (RentingDetail rentingDetail : rentingDetails) {
      Room room = roomRepostiory.findById(rentingDetail.getRoom().getRoomCode())
          .orElseThrow(() -> new NotFoundException("room-not-found"));
      room.setStatus(RoomStatus.Empty);
    }
    bookingCard.setStatus("Completed");

    List<GetBookingDetailResponse> getBookingDetailResponses = bookingDetailList.stream()
        .map(detail -> bookingDetailMapper.mapToGetBookingDetailResponse(detail))
        .collect(Collectors.toList());

    List<GetServiceDetailResponse> services = serviceDetailList.stream()
        .map(detail -> serviceDetailMapper.mapToServiceDetailResponse(detail))
        .collect(Collectors.toList());
    makePDF(response, getBookingCardForPaymentReponse, getBookingDetailResponses, services);
    return getBookingCardForPaymentReponse;
  }

  private void makePDF(
      HttpServletResponse response, GetBookingCardForPaymentReponse payment,
      List<GetBookingDetailResponse> bookingDetailList,
      List<GetServiceDetailResponse> serviceList) {
    try {
      Document document = new Document(PageSize.A4);
      PdfWriter.getInstance(document, response.getOutputStream());

      document.open();
      Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
      font1.setSize(22);
      font1.setColor(Color.PINK);

      Paragraph p = new Paragraph("INVOICE", font1);
      p.setAlignment(Paragraph.ALIGN_CENTER);

      Font font = FontFactory.getFont(FontFactory.COURIER_BOLD);
      font.setSize(14);
      font.setColor(Color.darkGray);
      Paragraph p1 = new Paragraph("", font);
      p.setAlignment(Paragraph.ALIGN_CENTER);
      Paragraph pBookingId = new Paragraph("Booking id: " + payment.getBookingId(), font);
      pBookingId.setAlignment(Paragraph.ALIGN_LEFT);

      Paragraph p2 = new Paragraph("", font);
      p.setAlignment(Paragraph.ALIGN_CENTER);
      Paragraph pFullName = new Paragraph(
          "Full name: " + payment.getFirstName() + " " + payment.getFirstName(), font);
      pFullName.setAlignment(Paragraph.ALIGN_LEFT);

      Paragraph p3 = new Paragraph("", font);
      p.setAlignment(Paragraph.ALIGN_CENTER);
      Paragraph pCMND = new Paragraph("CMND: " + payment.getCMND(), font);
      pCMND.setAlignment(Paragraph.ALIGN_LEFT);

      Paragraph p4 = new Paragraph("", font);
      p.setAlignment(Paragraph.ALIGN_CENTER);
      Paragraph pComplete = new Paragraph("Complete At: " + payment.getCompleteAt(), font);
      pComplete.setAlignment(Paragraph.ALIGN_LEFT);

      Paragraph p5 = new Paragraph("\n", font);
      Paragraph p7 = new Paragraph("ROOM", font);

      document.add(p);
      document.add(p1);
      document.add(pBookingId);
      document.add(p2);
      document.add(pFullName);
      document.add(p3);
      document.add(pCMND);
      document.add(p4);
      document.add(pComplete);
      document.add(p5);
      document.add(p7);

      PdfPTable table = new PdfPTable(3);
      table.setWidthPercentage(100f);
      table.setWidths(new float[]{1.5f, 1.5f, 3.0f});
      table.setSpacingBefore(20);

      writeTableData(table, bookingDetailList);

      Paragraph pRoomTotal = new Paragraph("Room total: " + payment.getRoomAmount(), font);
      pRoomTotal.setAlignment(Paragraph.ALIGN_RIGHT);
      Paragraph p8 = new Paragraph("\n", font);
      Paragraph p10 = new Paragraph("Service", font);
      document.add(table);
      document.add(pRoomTotal);
      document.add(p8);
      document.add(p10);

      PdfPTable table1 = new PdfPTable(3);
      table1.setWidthPercentage(100f);
      table1.setWidths(new float[]{1.5f, 1.5f, 3.0f});
      table1.setSpacingBefore(20);
      writeTableDataService(table1, serviceList);
      document.add(table1);

      Paragraph pServiceTotal = new Paragraph("Service total: " + payment.getServiceAmount(), font);
      pServiceTotal.setAlignment(Paragraph.ALIGN_RIGHT);
      document.add(pServiceTotal);

      Paragraph p6 = new Paragraph("\n", font);
      document.add(p6);

      Integer total = payment.getServiceAmount() + payment.getRoomAmount();
      Paragraph pTotal = new Paragraph("Pay total: " + total, font);
      pTotal.setAlignment(Paragraph.ALIGN_CENTER);
      document.add(pTotal);

      document.close();
    } catch (IOException e) {
      throw new BadRequestException("cant-get-file");
    }

  }

  private void writeTableDataService(PdfPTable table,
      List<GetServiceDetailResponse> serviceList) {

    for (GetServiceDetailResponse serviceDetail : serviceList) {
      table.addCell(String.valueOf(serviceDetail.getServiceName()));
      table.addCell(String.valueOf(serviceDetail.getQuantity()));
      table.addCell(String.valueOf(serviceDetail.getPrice()));
    }

  }

  private void writeTableData(PdfPTable table, List<GetBookingDetailResponse> bookingDetailList) {

    for (GetBookingDetailResponse bookingDetail : bookingDetailList) {
      RoomType roomType = roomTypeRepository.getOne(bookingDetail.getTypeId());
      table.addCell(String.valueOf(roomType.getName()));
      table.addCell(String.valueOf(bookingDetail.getAmount()));
      table.addCell(String.valueOf(bookingDetail.getPrice()));
    }

  }


}
