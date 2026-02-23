import {
  DarkTheme,
  DefaultTheme,
  ThemeProvider,
} from "@react-navigation/native";

import { Stack } from "expo-router";
import { StatusBar } from "expo-status-bar";
import "react-native-reanimated";

import { useColorScheme } from "@/hooks/use-color-scheme";
import "../global.css";

import { queryClient } from "@/lib/api-client";
import { QueryClientProvider } from "@tanstack/react-query";

type AuthStatus = "LOGGED_IN" | "UNAUTHORIZED";

const authStatus: AuthStatus = "UNAUTHORIZED";

export default function RootLayout() {
  const colorScheme = useColorScheme();

  return (
    <QueryClientProvider client={queryClient}>
      <ThemeProvider value={colorScheme === "dark" ? DarkTheme : DefaultTheme}>
        <Stack screenOptions={{ headerShown: false }}>
          <Stack.Protected guard={authStatus === "UNAUTHORIZED"}>
            <Stack.Screen name="auth" />
          </Stack.Protected>

          <Stack.Protected guard={authStatus !== "UNAUTHORIZED"}>
            <Stack.Screen name="(tabs)" />
          </Stack.Protected>
        </Stack>
        <StatusBar style="auto" />
      </ThemeProvider>
    </QueryClientProvider>
  );
}
