import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Snackbar from '@material-ui/core/Snackbar';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';

const styles = theme => ({
  close: {
    padding: theme.spacing.unit / 2,
  },
});

class MessageDialog extends React.Component {
  constructor(props) {
	      super(props);
	      this.state = {open: true};
  }

  handleClick = () => {
    this.setState({ open: true });
  };
  
  handleClose = (event, reason) => {
	    if (reason === 'clickaway') {
	      return;
	    }

	    this.setState({ open: false });
  };
  // https://stackoverflow.com/questions/44121069/how-to-pass-params-with-history-push-in-react-router-v4
  render() {
    const { classes } = this.props;
    return (
    	      <div>
    	        <Snackbar
    	          anchorOrigin={{
    	            vertical: 'bottom',
    	            horizontal: 'left',
    	          }}
    	          open={this.state.open}
    	          autoHideDuration={6000}
    	          onClose={this.handleClose}
    	          ContentProps={{
    	            'aria-describedby': 'message-id',
    	          }}
    	          message={<span id="message-id">{this.props.location.state.message}</span>}
    	          action={[
    	            <IconButton
    	              key="close"
    	              aria-label="Close"
    	              color="inherit"
    	              className={classes.close}
    	              onClick={this.handleClose}
    	            >
    	              <CloseIcon />
    	            </IconButton>,
    	          ]}
    	        />
    	      </div>
    );
  }
}

MessageDialog.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(MessageDialog);

