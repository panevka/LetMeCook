import { Button } from "@/components/ui/button";
import { View } from "react-native";

export const LoginRoute = () => {
  return (
    <>
      <View className="flex-1 flex justify-center bg-black/95">
        <Button onPress={() => console.log('Button pressed!')}>Login</Button>
      </View>
    </>
  );
}
