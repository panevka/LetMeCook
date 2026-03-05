import { Button } from "@/components/ui/button";
import { makeRoute } from "@/features/navigation/api/make-route";
import { useAppNavigation } from "@/features/navigation/api/use-app-navigation";
import { View } from "react-native";

const LoginPage = () => {
  const navigation = useAppNavigation();

  return (
    <>
      <View className="flex-1 flex justify-center bg-black/95">
        <Button onPress={() => navigation.navigate("register")}
        >Login</Button>
      </View>
    </>
  );
}

export const loginRoute = makeRoute({
  path: 'login',
  component: LoginPage,
});
