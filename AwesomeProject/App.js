import React from 'react';
import {  FlatList, ActivityIndicator, Alert, AppRegistry, Button, StyleSheet, Text, View, TextInput } from 'react-native';


class FetchHttpExample extends React.Component {

  onPressSubmitButton() {
   this.onFetchLoginRecords();
  }

  constructor(props) {
    super(props);
 
   this.state = {
       emailAddress: "",
       passWord: "",
       isLoading: true
      };
  }  

  async onFetchLoginRecords() {
    var data = {
     email: this.state.emailAddress,
     password: this.state.passWord
    };
    try {
     let response = await fetch(
      "http://WTC-5288873-L1.corp.ds.fedex.com:8080/movies.json",
      {
        method: "GET",
        headers: {
         "Accept": "application/json",
         "Content-Type": "application/json"
        }
     }
    );
     if (response.status >= 200 && response.status < 300) {
        alert("authenticated successfully!!!");
     }
   } catch (errors) {

     alert(errors);
    } 
}


  render(){
    return (
      <View style={styles.container}>
       <TextInput
        ref="txtEmail"
        style={styles.textInput}
        placeholder="Email Address"
        keyboardType="email-address"
        returnKeyType="next"
        onSubmitEditing={event => {
        this.refs.txtPassword.focus();
        }}
        onChangeText={text => this.setState({ emailAddress: text })}
      />
      <TextInput
        ref="txtPassword"
        style={styles.textInput}
        placeholder="Password"
        returnKeyType="done"
        secureTextEntry={true}
        onChangeText={text => this.setState({ passWord: text })}
      />
       <Button
        title="Submit"
        color="#841584"
        onPress={this.onPressSubmitButton.bind(this)}
      />
     </View>
    );
  }

}


class FetchMoviesExample extends React.Component {

  constructor(props){
    super(props);
    this.state ={ isLoading: true}
  }

  componentDidMount(){
    return fetch('http://WTC-5288873-L1.corp.ds.fedex.com:8080/api/movies/')
      .then((response) => response.json())
      .then((responseJson) => { 

        this.setState({
          isLoading: false,
          dataSource: responseJson,
        }, function(){

        });

      })
      .catch((error) =>{
        console.error(error);
      });
  }

  render(){

      if(this.state.isLoading){
        return(
          <View  style = { styles.netwrokcontainer }>
            <ActivityIndicator/>
          </View>
        )
      }
  
      return(
        <View  style = { styles.netwrokcontainer }>
          <FlatList
            data={this.state.dataSource}
            renderItem={({item}) => <Text>{item.title}, {item.tagline}</Text>}
            keyExtractor={(item, index) => item.title}
          />
        </View>
      );
    }

}

class FetchExample extends React.Component {

    constructor(props){
      super(props);
      this.state ={ isLoading: true}
    }
  
    componentDidMount(){
      return fetch('https://facebook.github.io/react-native/movies.json')
        .then((response) => response.json())
        .then((responseJson) => { 
  
          this.setState({
            isLoading: false,
            dataSource: responseJson.movies,
          }, function(){
  
          });
  
        })
        .catch((error) =>{
          console.error(error);
        });
    }

    render(){

        if(this.state.isLoading){
          return(
            <View  style = { styles.netwrokcontainer }>
              <ActivityIndicator/>
            </View>
          )
        }
    
        return(
          <View  style = { styles.netwrokcontainer }>
            <FlatList
              data={this.state.dataSource}
              renderItem={({item}) => <Text>{item.title}, {item.releaseYear}</Text>}
              keyExtractor={(item, index) => item.title}
            />
          </View>
        );
      }

}

class ButtonBasics extends React.Component {
    _onPressButton() {
      Alert.alert('You tapped the button!')
    }
  
    render() {
      return (
        <View style={styles.container}>
          <View style={styles.buttonContainer}>
            <Button
              onPress={this._onPressButton}
              title="Press Me"
            />
          </View>
          <View style={styles.buttonContainer}>
            <Button
              onPress={this._onPressButton}
              title="Press Me"
              color="#841584"
            />
          </View>
          <View style={styles.alternativeLayoutButtonContainer}>
            <Button
              onPress={this._onPressButton}
              title="This looks great!"
            />
            <Button
              onPress={this._onPressButton}
              title="OK!"
              color="#841584" 
            />
          </View>
        </View>
      );
    }
  }

  
class Blink extends React.Component {
    constructor(props) {
      super(props);
      this.state = {isShowingText: true};
  
      // Toggle the state every second
      setInterval(() => {
        this.setState(previousState => {
          return { isShowingText: !previousState.isShowingText,
            isLoading: true };
        });
      }, 1000);
    }
  
    render() {
      let display = this.state.isShowingText ? this.props.text : ' ';
      return (
        <Text>{display}</Text>
      );
    }
  }

export default class App extends React.Component {
    render() {
        return ( <View style = { styles.container } >
            <Text> Open up App.js to start working on your app...! </Text> 
            <Text> Well Done!Thank God: ) </Text> 
            <Text> Changes you make will automatically reload. </Text> 
            <Text> Shake your phone to open the developer menu. </Text> 
            <Blink text='I love to blink' />
            <ButtonBasics  />
            <FetchExample />
            <FetchHttpExample  />
            <FetchMoviesExample  />
            </View>
    );
}
}

const styles = StyleSheet.create({
    container: {
     flex: 1,
     justifyContent: 'center',
    },
    netwrokcontainer: {
        flex: 2,
        justifyContent: 'center',
       },
    buttonContainer: {
      margin: 20
    },
    alternativeLayoutButtonContainer: {
      margin: 20,
      flexDirection: 'row',
      justifyContent: 'space-between'
    },
    textInput: {
      height: 40,
      textAlign: "center",
      borderWidth: 1,
      width: "80%"
    },
     buttonSubmit: {
      color: "#841584"
     }
  })