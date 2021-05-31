function removeEvent(id) {
    var event = calendar.getEventById(id);
    alert("Are You Remove Event " + event.title);
    event.remove();
}

function addEvent(event) {
    if (!isNaN(event.date.valueOf())) { // valid?
        calendar.addEvent({
            title: 'dynamic event',
            start: date,
            allDay: true
        });
        alert('Great. Now, update your database...');
    } else {
        alert('Invalid date.');
    }
}