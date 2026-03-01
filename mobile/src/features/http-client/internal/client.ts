import {
  QueryClient,
} from '@tanstack/react-query'

import { env } from "@/features/env/api";

export const queryClient = new QueryClient();

const ENDPOINTS = {
	authorize: "api/authorize/discord",
} as const;

export const API_URLS = Object.fromEntries(
  Object.entries(ENDPOINTS).map(([key, path]) => [
    key,
    `${env.EXPO_PUBLIC_API_URL}${path}`,
  ])
) as Readonly<Record<keyof typeof ENDPOINTS, string>>;
