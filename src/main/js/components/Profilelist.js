import React, {Component} from 'react';
import MUIDataTable from "mui-datatables";

// https://www.npmjs.com/package/mui-datatables
class Profilelist extends React.Component {
  constructor(props) {
    super(props);
    this.state = { cars: [], open: false, message: ''};
  }

  componentDidMount() {
	    this.fetchCars();
	  }
	  
	  // Fetch all cars
	  fetchCars = () => {
	    fetch('api/profile')
	    .then((response) => response.json()) 
	    .then((responseData) => { 
	      this.setState({ 
	        cars: responseData._embedded.cars,
	      }); 
	    })
	    .catch(err => console.error(err));   
	  }
  


  render() {
	  
	  const data = this.state.cars;
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