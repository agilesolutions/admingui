import React, {Component} from 'react';
import MUIDataTable from "mui-datatables";

// https://www.npmjs.com/package/mui-datatables
class Profilelist extends React.Component {
  constructor(props) {
    super(props);
    this.state = { profiles: [], open: false, message: ''};
  }

  componentDidMount() {
	    this.fetchCars();
	  }
	  
	  // Fetch all profiles
	  fetchCars = () => {
	    fetch('api/profiles')
	    .then((response) => response.json()) 
	    .then((responseData) => { 
	      this.setState({ 
	        profiles: responseData._embedded.profiles,
	      }); 
	    })
	    .catch(err => console.error(err));   
	  }
  


  render() {
	  
	  const data = this.state.profiles;
	  const rows = [];
	  
	  data.map(r => {
		  var row = [];
		  row.push(r.name);
		  row.push(r.host);
		  row.push(r.environment);
		  
		  rows.push(row);});
	  
	  
	    const columns = ["name", "host", "environment"];

	    const options = {
	      filter: true,
	      filterType: 'dropdown',
	      responsive: 'stacked',
	    };

	  

    return (
    	   <MUIDataTable title={"Profile list"} data={rows} columns={columns} options={options} />
      );
  }
}

export default Profilelist;