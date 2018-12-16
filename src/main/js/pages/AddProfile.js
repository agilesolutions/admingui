import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Snackbar from '@material-ui/core/Snackbar';

class AddProfile extends React.Component {
  constructor(props) {
      super(props);
      this.state = {name: '', host: '',  environment: '', message: ''};
  }

  handleChange = (event) => {
      this.setState(
          {[event.target.name]: event.target.value}
      );
  }    

  addProfile = () => {
    var newProfile = {name: this.state.name, host: this.state.host, 
    	        environment: this.state.environment};
    fetch('api/profiles', 
    {   method: 'POST', 
        headers: {
          'Content-Type': 'application/json'},
        body: JSON.stringify(newProfile)
    })
    .then(() => {this.state.message = 'record saved';})
    .catch(err => console.error(err));
  }
  
  
  cancelSubmit = (event) => {
    event.preventDefault();    
    this.refs.addDialog.hide();    
  }
  //https://github.com/xotahal/react-native-material-ui/issues/258
  render() {
    return (
      <div>

          <h3>New profile</h3>
          <form>
            <TextField label="Name" placeholder="Name"  name="name" onChange={this.handleChange}/><br/>
            <TextField label="Host" placeholder="Host" name="host" onChange={this.handleChange}/><br/>
            <TextField label="Environment" placeholder="Environment" name="environment" onChange={this.handleChange}/><br/>
          </form>     

        <div>
            <Button variant="raised" color="primary" style={{'margin': '10px'}} onClick={this.addProfile}>Save Profile</Button>
        </div>
        <div>
        	<Snackbar ref="snackbar" message= { this.state.message } visible={ this.state.message ? true : false }/>
        </div>
      </div>   
    );
  }
}

export default AddProfile;