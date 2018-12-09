import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

class addprofile extends React.Component {
  constructor(props) {
      super(props);
      this.state = {name: '', host: '',  environment: ''};
  }

  handleChange = (event) => {
      this.setState(
          {[event.target.name]: event.target.value}
      );
  }    
  
  // Save car and close modal form
  handleSubmit = (event) => {
      event.preventDefault();
      var newProfile = {name: this.state.name, host: this.state.host, 
        environment: this.state.environment};
      this.props.addprofile(newProfile);    
      this.refs.addDialog.hide();    
  }

  cancelSubmit = (event) => {
    event.preventDefault();    
    this.refs.addDialog.hide();    
  }
  
  render() {
    return (
      <div>

          <h3>New car</h3>
          <form>
            <TextField label="Name" placeholder="Name"  name="name" onChange={this.handleChange}/><br/>
            <TextField label="Host" placeholder="Host" name="host" onChange={this.handleChange}/><br/>
            <TextField label="Environment" placeholder="Environment" name="environment" onChange={this.handleChange}/><br/>
            <Button variant="outlined" style={{marginRight: 10}} color="primary" onClick={this.handleSubmit}>Save</Button>        
            <Button variant="outlined" color="secondary" onClick={this.cancelSubmit}>Cancel</Button>        
          </form>     

        <div>
            <Button variant="raised" color="primary" style={{'margin': '10px'}} onClick={() => this.refs.addDialog.show()}>New Car</Button>
        </div>
      </div>   
    );
  }
}

export default addprofile;