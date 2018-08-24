import React, { Component } from 'react';
import {
  AppRegistry,
  ActivityIndicator,
  StyleSheet,
  Text,
  View,
  Image,
  StatusBar,
  Platform
} from 'react-native';
import { Constants, Font } from 'expo';
import Swiper from 'react-native-swiper';
import { Planet, BottomBar } from './components';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'stretch',
    justifyContent: 'flex-start',
    paddingTop: Constants.statusBarHeight,
    backgroundColor: 'black',
  },
  fill: {
    position: 'absolute',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
  },
  title: {
    fontFamily: 'dhurjati',
    fontSize: 32,
    color: 'white',
    backgroundColor: 'transparent',
    textAlign: 'center',
    marginTop: 8,
    letterSpacing: 1.6,
    zIndex: 2,
    alignSelf: 'center'
  },
  titlePlatformSpecific: Platform.select({
    ios: {
      marginBottom: 10,
    },
  }),
  wrapper: {
  },
  slide1: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    //backgroundColor: '#9DD6EB',
  },
  slide2: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    //backgroundColor: '#97CAE5',
  },
  slide3: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    //backgroundColor: '#92BBD9',
  },
  text: {
    color: '#fff',
    fontSize: 30,  
    fontFamily: 'Arial', 
    fontWeight: 'bold',
  }
})

const slides = [
  {title: 'Hello Swiper'},
  {title: 'Beautiful'},
  {title: 'And simple'}
];

export default class App extends Component {
  constructor(props){
    super(props);
    this.state ={ isLoading: true,
      currentIndex: 0,
      fontsLoaded: false ,
      dataSource: []
    }
  }

  componentDidMount = async () => {
    await Font.loadAsync({ 
      dhurjati: require('./assets/Dhurjati-Regular.ttf'),
      'inconsolata-regular': require('./assets/Inconsolata-Regular.ttf'),
      'inconsolata-bold': require('./assets/Inconsolata-Bold.ttf'),
      'libre-barcode-39': require('./assets/LibreBarcode39-Regular.ttf'),
      'Arial': require('./assets/Inconsolata-Bold.ttf')
    });

    this.setState({ fontsLoaded: true });

    return fetch('http://WTC-5288873-L1.corp.ds.fedex.com:8080/api/movies/')
    .then((response) => response.json())
    .then((responseJson) => { 

      this.setState({
        isLoading: false,
        dataSource: responseJson,
      }, function(){
        console.log("Successfully retrieve'd movies list...");
      });

    })
    .catch((error) =>{
      console.error(error);
    });

  };

  render(){ 
    return ( !this.state.fontsLoaded && this.state.isLoading
        ? <View style={[styles.container, { justifyContent: 'center' }]}>
            <ActivityIndicator color="white" />
          </View>
        :
      <View style={styles.container}> 
        <StatusBar barStyle="light-content" />
        <Image
          resizeMode="contain"
          style={styles.fill}
          source={require('./assets/space-bg.jpg')}
        /> 
      <Swiper style={styles.wrapper} showsButtons={true} 
            onMomentumScrollEnd={(e, state, context) => {
              console.log('index:',  state.index)
              this.setState(() => ({ currentIndex: state.index }))
              }   
            }
            dot={<View style={{backgroundColor: 'rgba(0,0,0,.2)', width: 5, height: 5, borderRadius: 4, marginLeft: 3, marginRight: 3, marginTop: 3, marginBottom: 3}} />}
            activeDot={<View style={{backgroundColor: '#000', width: 8, height: 8, borderRadius: 4, marginLeft: 3, marginRight: 3, marginTop: 3, marginBottom: 3}} />}
            paginationStyle={{
              bottom: -23, left: null, right: 10
            }}
              index = { this.state.key } >
          { this.state.dataSource.map((item, key) => {
            return (
              <View key = { key } style = { styles.slide1 }>              
                <Image resizeMode='stretch' source={{ uri: item.imageUrl }} style={{width: 293, height: 210}} />
                <View> 
                  <Text style={styles.text}>{ item.title}</Text> 
                </View>
              </View> 
            ) 
          })} 
      </Swiper>

      <Text style={[styles.title, styles.titlePlatformSpecific]}>
        SPACED
      </Text>

      <BottomBar destination={this.state.dataSource.length==0?'A123':this.state.dataSource[this.state.currentIndex].title} />

      </View>
    );
  }
}


AppRegistry.registerComponent('App', () => App);