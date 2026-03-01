import { z } from 'zod'

const envSchema = z.object({
  EXPO_PUBLIC_API_URL: z.string().url(),
});

type Env = z.infer<typeof envSchema>;

export const env = envSchema.parse(process.env);
