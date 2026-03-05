import { makeRoute } from "@/features/navigation/api/make-route";
import { Text } from "react-native";

export const RegistrationPage = () => {
  return <Text> Register Page </Text>;
}

export const registrationRoute = makeRoute({
  path: "register",
  component: RegistrationPage,
});
