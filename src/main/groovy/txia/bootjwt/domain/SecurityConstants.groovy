package txia.bootjwt.domain

class SecurityConstants {
     static final String SECRET = 'ChewbaccaXia'
//     static final Long EXPIRATION_TIME = 864_000_000; // 10 days
     static final Long EXPIRATION_TIME = 60_000; // 1 minute
     static final String TOKEN_PREFIX = 'Bearer '
     static final String HEADER_STRING = 'Authorization'
     static final String SIGN_UP_URL = '/users/sign-up'
     static final String API_DOCUMENTATION_URL = '/api/**'
}
