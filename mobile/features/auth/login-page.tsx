import { Linking, Button } from 'react-native';
import { API_URLS } from '@/lib/api-client';

export function LoginPage() {

  const onPress = () => {
    Linking.openURL(API_URLS.authorize);
  }

  return (
    <Button
      onPress={onPress}
      title="Learn More"
      color="#841584"
      accessibilityLabel="Learn more about this purple button"
    />
  );

}
