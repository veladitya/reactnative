import React, { Component } from 'react';
import {
  AppRegistry,
  ActivityIndicator,
  StyleSheet,
  Text,
  View
} from 'react-native';
import { Constants, Font } from 'expo';
import Swiper from 'react-native-swiper';

const styles = StyleSheet.create({
  wrapper: {
  },
  slide1: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#9DD6EB',
  },
  slide2: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#97CAE5',
  },
  slide3: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#92BBD9',
  },
  text: {
    color: '#fff',
    fontSize: 30,
    fontFamily: 'Inconsolata-Bold',
    fontWeight: 'bold',
  }
})

export default class App extends Component {
    state = {
        currentIndex: 0,
        fontsLoaded: false,
      };

    componentDidMount = async () => {
        await Font.loadAsync({ 
          dhurjati: require('./assets/Dhurjati-Regular.ttf'),
          'inconsolata-regular': require('./assets/Inconsolata-Regular.ttf'),
          'inconsolata-bold': require('./assets/Inconsolata-Bold.ttf'),
          'libre-barcode-39': require('./assets/LibreBarcode39-Regular.ttf'),
        });

        this.setState({ fontsLoaded: true });
      };

  render(){ 
    return ( !this.state.fontsLoaded
        ? <View style={[styles.container, { justifyContent: 'center' }]}>
            <ActivityIndicator color="white" />
          </View>
        :
      <Swiper style={styles.wrapper} showsButtons={true}>
        <View style={styles.slide1}>
          <Text style={styles.text}>Hello Swiper</Text>
        </View>
        <View style={styles.slide2}>
          <Text style={styles.text}>Beautiful</Text>
        </View>
        <View style={styles.slide3}>
          <Text style={styles.text}>And simple</Text>
        </View>
      </Swiper>
    );
  }
}

AppRegistry.registerComponent('App', () => App);