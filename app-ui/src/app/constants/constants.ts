export const API_BASE_URL = 'http://localhost:8080/api/v1';
export const ACCESS_TOKEN = 'accessToken';

export const OAUTH2_REDIRECT_URI = 'http://localhost:4200/oauth2/redirect';

export const GOOGLE_AUTH_URL = `${API_BASE_URL}/oauth2/authorize/google?redirect_uri=${OAUTH2_REDIRECT_URI}`;
export const FACEBOOK_AUTH_URL = `${API_BASE_URL}/oauth2/authorize/facebook?redirect_uri=${OAUTH2_REDIRECT_URI}`;
export const GITHUB_AUTH_URL = `${API_BASE_URL}/oauth2/authorize/github?redirect_uri=${OAUTH2_REDIRECT_URI}`;

export const CURRENT_USER_URL = `${API_BASE_URL}/users/current-user`;

export const SIGNIN_URL = `${API_BASE_URL}/auth/login`;
export const SIGNUP_URL = `${API_BASE_URL}/auth/register`;
export const LOGOUT_URL = `${API_BASE_URL}/auth/logout`;

export const FORGOT_PASSWORD_URL = `${API_BASE_URL}/auth/forgot-password`;
export const RESET_PASSWORD_URL = `${API_BASE_URL}/auth/reset-password`;
