import {
  DarkTheme,
  DefaultTheme,
  ThemeProvider,
} from "@react-navigation/native";

import { StatusBar } from "expo-status-bar";
import "react-native-reanimated";

import { ThemeProvider as BnaThemeProvider } from "@/theme/theme-provider";

import { useColorScheme } from "@/hooks/use-color-scheme";
import "../global.css";

import { queryClient } from "@/lib/api-client";
import { QueryClientProvider } from "@tanstack/react-query";

export default function RootLayout() {
  const colorScheme = useColorScheme();

  return (
    <QueryClientProvider client={queryClient}>
      <BnaThemeProvider>
        <ThemeProvider
          value={colorScheme === "dark" ? DarkTheme : DefaultTheme}
        >
          <StatusBar style="auto" />
        </ThemeProvider>
      </BnaThemeProvider>
    </QueryClientProvider>
  );
}
