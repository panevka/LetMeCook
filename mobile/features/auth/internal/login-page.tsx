import {  View } from 'react-native';
import { Button } from '@/components/ui/button';

export function LoginPage() {

  return (
    <>
      <View className="flex-1 flex justify-center bg-black/95">

        <Button onPress={() => console.log('Button pressed!')}>Login</Button>
      </View>
    </>
  );

}
