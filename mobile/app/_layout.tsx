import {
  DarkTheme,
  DefaultTheme,
  ThemeProvider,
} from "@react-navigation/native";

import { Stack } from "expo-router";
import { StatusBar } from "expo-status-bar";
import "react-native-reanimated";

import { ThemeProvider as BnaThemeProvider } from "@/theme/theme-provider";

import { useColorScheme } from "@/hooks/use-color-scheme";
import "../global.css";

import { queryClient } from "@/lib/api-client";
import { QueryClientProvider } from "@tanstack/react-query";
import { useAuthenticaton } from "@/features/auth/api";

export default function RootLayout() {
  const colorScheme = useColorScheme();
  const { isAuthenticated } = useAuthenticaton();

  return (
    <QueryClientProvider client={queryClient}>
      <BnaThemeProvider>
        <ThemeProvider
          value={colorScheme === "dark" ? DarkTheme : DefaultTheme}
        >
          <Stack screenOptions={{ headerShown: false }}>
            <Stack.Protected guard={!isAuthenticated}>
              <Stack.Screen name="auth" />
            </Stack.Protected>

            <Stack.Protected guard={isAuthenticated}>
              <Stack.Screen name="(tabs)" />
            </Stack.Protected>
          </Stack>
          <StatusBar style="auto" />
        </ThemeProvider>
      </BnaThemeProvider>
    </QueryClientProvider>
  );
}
