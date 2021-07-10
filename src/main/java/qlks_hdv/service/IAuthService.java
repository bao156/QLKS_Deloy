package qlks_hdv.service;

import qlks_hdv.request.SigninRequest;

public interface IAuthService {

  Boolean authenticateUser(SigninRequest signinRequest);
}
