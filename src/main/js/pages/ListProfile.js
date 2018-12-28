import React, {Component} from 'react';
import MUIDataTable from "mui-datatables";
import MessageDialog, { openDialog } from '../dialogs/MessageDialog';
import ConfirmDialog, { askConfirmation } from '../dialogs/ConfirmDialog';
import Store from '../data/Store';


// https://www.npmjs.com/package/mui-datatables
class ListProfile extends React.Component {

  static contextType = Store;

  constructor(props) {
    super(props);
    this.state = { profiles: [], open: false, message: ''};
  }

  // https://stackoverflow.com/questions/33237200/fetch-response-json-gives-responsedata-undefined
  // https://stackoverflow.com/questions/36840396/react-fetch-gives-an-empty-response-body
  componentDidMount() {
	    this.fetchCars();
	  }
	  
  componentDidMount() {
	    this.fetchCars();
	  }
	  
	  // Fetch all profiles
	  fetchCars = () => {
	    fetch('services/profiles')
	    .then((response) => response.json()) 
	    .then((responseData) => {
	      console.log(responseData)
	      this.setState({ 
	        profiles: responseData,
	      }); 
	    })
	    .catch(err => console.error(err));   
	  }


  render() {
	  
	  const data = this.state.profiles;
	  const rows = [];
	  
	  data.map(r => {
		  var row = [];
		  row.push(r.id);
		  row.push(r.domain);
		  row.push(r.name);
		  row.push(r.host);
		  row.push(r.environment);
		  row.push(r.ticket);
		  row.push(r.template);
		  
		  rows.push(row);});
	  
	  	
	    const columns = ["id", "domain", "name", "host", "environment", "ticket", "template"];

	    const options = {
	      filter: true,
	      filterType: 'dropdown',
	      responsive: 'stacked',
	      onRowsDelete: (rowsDeleted) => {
	        askConfirmation({message: 'Do you want to continue?.'});
	      	rowsDeleted.data.map(r => {
	      		fetch('api/profiles/' + rows[r.dataIndex][0], {method: 'DELETE'})
    				.then(() => console.log('Record ' + rows[r.dataIndex][1] + ' on host ' + rows[r.dataIndex][2] + ' removed!'))
    				.catch(err => console.error(err));
	      		
	      		}	
	      	)
	      	openDialog({message: 'Selected records deleted.'});
      	  },
	      onRowClick: (rowsData) => {
		      	this.context.id = rowsData[0];
		      	this.context.domain = rowsData[1];
		      	this.context.name = rowsData[2];
		      	this.context.host = rowsData[3];
		      	this.context.environment = rowsData[4];
		      	this.context.ticket = rowsData[5];
		      	this.context.template = rowsData[6];
	      	console.log(this.context);
				        
      	  }	      
	    };

    return (
       <div>
          <div>
    	   	<MUIDataTable title={"Profile list"} data={rows} columns={columns} options={options}/>
    	  </div>
          <div>
           	<MessageDialog/>
          </div>
      </div>
      );
  }
}

export default ListProfile;