import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Snackbar from '@material-ui/core/Snackbar';
import MessageDialog, { openDialog } from '../dialogs/MessageDialog';
import {newJob} from   '../logic/Logic'
import Store from '../data/Store';

class DeployProfile extends React.Component {

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
    var newProfile = {id: this.context.id, domain: this.context.domain, name: this.context.name, host: this.context.host, 
    	        environment: this.context.environment, ticket: this.context.ticket, template: this.context.template};
    fetch('services/startjob',
    	    {   method: 'POST', 
        headers: {
          'Content-Type': 'application/json'},
          body: JSON.stringify(newProfile)
    })
       .then(() => {openDialog({ message: 'Deployment pipeline on ' + this.context.template + ' started.' });
    			})
	.catch(err => console.error(err));
  }
  
  cancelSubmit = (event) => {
    event.preventDefault();    
    this.refs.addDialog.hide();    
  }
  //https://github.com/xotahal/react-native-material-ui/issues/258
  render() {
	  
	  //this.setState({domain: this.context.domain, name: this.context.name,host: this.context.host,environment: this.context.environment, ticket: this.context.ticket });
	  
    return (
      <div>

          <h3>Deploy Service</h3>
          <form>
          <TextField label="Domain" placeholder="domain"  name="domain" value={this.context.domain} onChange={this.handleChange}/><br/>
          <TextField label="Name" placeholder="Name"  name="name" value={this.context.name} onChange={this.handleChange}/><br/>
            <TextField label="Host" placeholder="Host" name="host" value={this.context.host} onChange={this.handleChange}/><br/>
            <TextField label="Environment" placeholder="Environment" name="environment" value={this.context.environment} onChange={this.handleChange}/><br/>
            <TextField label="Ticket" placeholder="ticket" name="ticket" value={this.context.ticket} onChange={this.handleChange}/><br/>
            <TextField label="Template" placeholder="template" name="template" value={this.context.template} onChange={this.handleChange}/><br/>
          </form>     

        <div>
            <Button variant="raised" color="primary" style={{'margin': '10px'}} onClick={this.addProfile}>Deploy service</Button>
        </div>
        <div>
        	<MessageDialog/>
        </div>
      </div>   
    );
  }
}

export default DeployProfile;