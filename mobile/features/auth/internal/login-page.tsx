import { Linking, View } from 'react-native';
import { Button } from '@/components/ui/button';
import { API_URLS } from '@/lib/api-client';
import { useAuthenticaton } from './use-authentication';

export function LoginPage() {

  const { isAuthenticated } = useAuthenticaton();

  const onPress = () => {
    Linking.openURL(API_URLS.authorize);
  }

  return (
    <>
      <View className="flex-1 flex justify-center bg-black/95">

        <Button onPress={() => console.log('Button pressed!')}>Login</Button>
      </View>
    </>
  );

}
