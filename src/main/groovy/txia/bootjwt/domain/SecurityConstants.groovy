package txia.bootjwt.domain

class SecurityConstants {
     static final String SECRET = 'ChewbaccaXia'
     static final Long EXPIRATION_TIME = 864_000_000; // 10 days
     static final String TOKEN_PREFIX = 'Bearer '
     static final String HEADER_STRING = 'Authorization'
     static final String SIGN_UP_URL = '/users/sign-up'
}
