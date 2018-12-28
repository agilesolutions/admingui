import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Snackbar from '@material-ui/core/Snackbar';
import MessageDialog, { openDialog } from '../dialogs/MessageDialog';
import {newJob} from   '../logic/Logic'
import Store from '../data/Store';

class AddProfile extends React.Component {

  static contextType = Store;
  
  constructor(props) {
      super(props);
      this.state = {domain: '', name: '', host: '',  environment: '', ticket: '', template: ''};
  }

  handleChange = (event) => {
      this.setState(
          {[event.target.name]: event.target.value}
      );
  }    

  addProfile = () => {
    var newProfile = {domain: this.state.domain, name: this.state.name, host: this.state.host, 
    	        environment: this.state.environment, ticket: this.state.ticket, template: this.state.template};
    fetch('services/addprofile', 
    {   method: 'POST', 
        headers: {
          'Content-Type': 'application/json'},
        body: JSON.stringify(newProfile)
    })
    .catch(err => console.error(err));
    var submitProfile = {id: 1, domain: this.state.domain, name: this.state.name, host: this.state.host, 
	        environment: this.state.environment, ticket: this.state.ticket, template: this.state.template};
    
    fetch('services/createjob',
    	    {   method: 'POST', 
        headers: {
          'Content-Type': 'application/json'},
          body: JSON.stringify(newProfile)
    })
    .then(() => {openDialog({ message: 'New Jenkins Job created.' });
	})
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
          <TextField label="Domain" placeholder="domain"  name="domain" onChange={this.handleChange}/><br/>
          <TextField label="Name" placeholder="Name"  name="name" onChange={this.handleChange}/><br/>
            <TextField label="Host" placeholder="Host" name="host" onChange={this.handleChange}/><br/>
            <TextField label="Environment" placeholder="Environment" name="environment" onChange={this.handleChange}/><br/>
            <TextField label="Ticket" placeholder="ticket" name="ticket" onChange={this.handleChange}/><br/>
            <TextField label="Template" placeholder="template" name="template" onChange={this.handleChange}/><br/>
          </form>     

        <div>
            <Button variant="raised" color="primary" style={{'margin': '10px'}} onClick={this.addProfile}>Save Profile</Button>
        </div>
        <div>
        	<MessageDialog/>
        </div>
      </div>   
    );
  }
}

export default AddProfile;