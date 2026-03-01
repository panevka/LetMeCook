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

import { ThemeProvider as BnaThemeProvider } from "@/features/theme/api";

import { useColorScheme } from "@/features/theme/api";
import "@/features/theme/api/global.css";

import { queryClient } from "@/features/http-client/api";
import { QueryClientProvider } from "@tanstack/react-query";
import { LoginRoute } from '@/features/auth/api';

const RootStack = createNativeStackNavigator({
  screens: {
    Home: LoginRoute,
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
