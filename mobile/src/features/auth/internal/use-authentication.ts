type AuthenticationStatus = "LOGGED_IN" | "LOGGED_OUT";

const authenticationStatus: AuthenticationStatus = "LOGGED_OUT";

export const useAuthenticaton = () => {
  const isAuthenticated = authenticationStatus !== "LOGGED_OUT";
  return { isAuthenticated };
}

