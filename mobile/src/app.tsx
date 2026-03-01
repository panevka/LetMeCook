import { createStaticNavigation } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { registerRootComponent} from 'expo'
import {
  DarkTheme,
  DefaultTheme,
  ThemeProvider,
} from "@react-navigation/native";

import { StatusBar } from "expo-status-bar";
import "react-native-reanimated";

import { ThemeProvider as BnaThemeProvider } from "@/lib/theme/theme-provider";

import { useColorScheme } from "@/hooks/use-color-scheme";
import "@/lib/global.css";

import { queryClient } from "@/lib/api-client";
import { QueryClientProvider } from "@tanstack/react-query";
import { LoginPage } from '@/features/auth/api';

const RootStack = createNativeStackNavigator({
  screens: {
    Home: LoginPage,
  },
});

const Navigation = createStaticNavigation(RootStack);

registerRootComponent(App);

function App () {
  const colorScheme = useColorScheme();

  return (
    <QueryClientProvider client={queryClient}>
      <BnaThemeProvider>
        <ThemeProvider
          value={colorScheme === "dark" ? DarkTheme : DefaultTheme}
        >
          <Navigation />
          <StatusBar style="auto" />
        </ThemeProvider>
      </BnaThemeProvider>
    </QueryClientProvider>
  );
}
