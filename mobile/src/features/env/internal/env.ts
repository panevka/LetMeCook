import { z } from 'zod'

const envSchema = z.object({
  EXPO_PUBLIC_API_URL: z.url(),
});

type Env = z.infer<typeof envSchema>;

const env: Readonly<Env> = envSchema.parse(process.env);
Object.freeze(env); // runtime precaution

export { env };
