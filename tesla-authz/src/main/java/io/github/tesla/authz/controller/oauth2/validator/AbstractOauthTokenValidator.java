package io.github.tesla.authz.controller.oauth2.validator;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.tesla.authz.controller.oauth2.OAuthTokenxRequest;
import io.github.tesla.authz.utils.MD5Utils;


public abstract class AbstractOauthTokenValidator extends AbstractClientDetailsValidator {

  private static final Logger LOG = LoggerFactory.getLogger(AbstractOauthTokenValidator.class);

  protected OAuthTokenxRequest tokenRequest;

  protected AbstractOauthTokenValidator(OAuthTokenxRequest tokenRequest) {
    super(tokenRequest);
    this.tokenRequest = tokenRequest;
  }


  protected String grantType() {
    return tokenRequest.getGrantType();
  }


  protected OAuthResponse invalidGrantTypeResponse(String grantType) throws OAuthSystemException {
    return OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
        .setError(OAuthError.TokenResponse.INVALID_GRANT)
        .setErrorDescription("Invalid grant_type '" + grantType + "'").buildJSONMessage();
  }


  protected boolean invalidUsernamePassword() {
    final String username = tokenRequest.getUsername();
    String password = tokenRequest.getPassword();
    password = MD5Utils.encrypt(username, password);
    try {
      SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
    } catch (Exception e) {
      LOG.debug("Login failed by username: " + username, e);
      return true;
    }
    return false;
  }

  protected OAuthResponse invalidUsernamePasswordResponse() throws OAuthSystemException {
    return OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
        .setError(OAuthError.TokenResponse.INVALID_GRANT).setErrorDescription("Bad credentials")
        .buildJSONMessage();
  }

}
