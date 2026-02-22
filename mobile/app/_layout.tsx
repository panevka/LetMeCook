import {
  DarkTheme,
  DefaultTheme,
  ThemeProvider,
} from "@react-navigation/native";
import { Stack } from "expo-router";
import { StatusBar } from "expo-status-bar";
import "react-native-reanimated";

import { useColorScheme } from "@/hooks/use-color-scheme";

type AuthStatus = "LOGGED_IN" | "UNAUTHORIZED";

const authStatus: AuthStatus = "UNAUTHORIZED";

export default function RootLayout() {
  const colorScheme = useColorScheme();

  return (
    <ThemeProvider value={colorScheme === "dark" ? DarkTheme : DefaultTheme}>
      <Stack>
        <Stack.Protected guard={authStatus === "UNAUTHORIZED"}>
          <Stack.Screen name="auth" />
        </Stack.Protected>

        <Stack.Protected guard={authStatus !== "UNAUTHORIZED"}>
          <Stack.Screen name="(tabs)" />
        </Stack.Protected>
      </Stack>
      <StatusBar style="auto" />
    </ThemeProvider>
  );
}
