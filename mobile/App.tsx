import RootLayout from "./app/_layout";
import { createStaticNavigation } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

const RootStack = createNativeStackNavigator({
  screens: {
    Home: RootLayout,
  },
});

const Navigation = createStaticNavigation(RootStack);

const App = () => {
  return (

<Navigation />);

}

export default App;
