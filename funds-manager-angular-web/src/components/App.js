import React from 'react';
import autoBind from 'react-autobind';
import brandImage from '../images/logo.svg';
import {NavLink, Route} from 'react-router-dom';
import {Redirect, Switch} from 'react-router';
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem} from 'reactstrap';
import FundsPage from './funds/FundsPage';
import ResearchPage from './research/ResearchPage';
import AboutPage from './about/AboutPage';
import Theme from './Theme';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isOpen: false,
      theme: Theme.getTheme()
    };

    autoBind(this);
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  toggleTheme(e) {
    const theme = e.target.checked ? 'dark' : 'light';
    Theme.setTheme(theme);
    this.setState({
      theme: theme
    });
  }

  render() {
    return (
      <div className="fixed-layout">
        <header>
          <Navbar expand="md">
            <NavbarBrand tag={NavLink} to="/">
              <img src={brandImage} /><strong>Funds Manager</strong>
            </NavbarBrand>

            <NavbarToggler onClick={this.toggle}>
              <i className="fas fa-bars" />
            </NavbarToggler>

            <Collapse isOpen={this.state.isOpen} navbar>
              <Nav className="mr-auto" navbar>
                <NavItem>
                  <NavLink className="nav-link" to="/funds">Funds</NavLink>
                </NavItem>
                <NavItem>
                  <NavLink className="nav-link" to="/research">Research</NavLink>
                </NavItem>
                <NavItem>
                  <NavLink className="nav-link" to="/about">About</NavLink>
                </NavItem>
              </Nav>
              <ul className="mr-0 nav">
                <div className="theme-switcher nav-item">
                  <span className="light">Light</span>
                  <input id="toggler" className="toggler" type="checkbox" onChange={this.toggleTheme} checked={this.state.theme === 'dark'} />
                  <label htmlFor="toggler" />
                  <span className="dark">Dark</span>
                </div>
              </ul>
            </Collapse>
          </Navbar>
        </header>

        <main>
          <Switch>
            <Route path="/about" component={AboutPage} />
            <Route path="/research" component={ResearchPage} />
            <Route path="/funds" component={FundsPage} />
            <Redirect from="/" to="/funds" />
          </Switch>
        </main>

        <footer className="container-fluid">
         Funds Manager
        </footer>

      </div>
    );
  }
}

export default App;
