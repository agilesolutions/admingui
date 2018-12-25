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
      this.state = {domain: 'crm', name: 'crmapp', host: 'srp99999lx',  environment: 'prd', ticket: 'CRM-1'};
  }

  handleChange = (event) => {
      this.setState(
          {[event.target.name]: event.target.value}
      );
  }    

  addProfile = () => {
    var newProfile = {domain: this.state.domain, name: this.state.name, host: this.state.host, 
    	        environment: this.state.environment, ticket: this.state.ticket};
    fetch('services/startjob/' + this.state.name + '-' + this.state.environment)
       .then(() => {openDialog({ message: 'Deployment pipeline started.' });
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

          <h3>Deploy Service</h3>
          <form>
          <TextField label="Domain" placeholder="domain"  name="domain" onChange={this.handleChange}/><br/>
          <TextField label="Name" placeholder="Name"  name="name" onChange={this.handleChange}/><br/>
            <TextField label="Host" placeholder="Host" name="host" onChange={this.handleChange}/><br/>
            <TextField label="Environment" placeholder="Environment" name="environment" onChange={this.handleChange}/><br/>
            <TextField label="Ticket" placeholder="ticket" name="ticket" onChange={this.handleChange}/><br/>
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