import { Component } from 'react';
import './App.css';
import PaymentCreate from './components/PaymentCreate';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component {

  async componentDidMount() {
    const response = await fetch('/api/transaction/request');
    const body = await response.json();
    this.setState({clients: body});
  }

  render() {
    return (
        <Router>
          <Switch>
            <Route path='/api/transaction/request' exact={true} component={PaymentCreate}/>
          </Switch>
        </Router>
    )
  }
}
export default App;