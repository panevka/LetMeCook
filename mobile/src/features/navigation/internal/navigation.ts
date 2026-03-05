import { loginRoute, registrationRoute } from "@/features/auth/api";
import { createStaticNavigation } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { JSX } from "react";

const routesRegistry = [loginRoute, registrationRoute] as const;

type Routes = typeof routesRegistry[number];
type AllPaths = Routes["path"];

const routes: Record<AllPaths, () => JSX.Element> = Object.fromEntries(
  routesRegistry.map(r => [r.path, r.component])
) as Record<AllPaths, () => JSX.Element>;

export type RootStackParamsMap = {
  [R in Routes as R["path"]]: R["params"];
};

const rootStack = createNativeStackNavigator({
  screenOptions: {
    headerShown: false
  },
  screens: routes
});

export const Navigation = createStaticNavigation(rootStack);
