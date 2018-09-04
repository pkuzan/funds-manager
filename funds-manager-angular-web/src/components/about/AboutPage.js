import React from 'react';
import {NavLink} from 'react-router-dom';

class AboutPage extends React.Component {
  render() {
    return (
      <div>
        {this.renderAbout()}
      </div>
    );
  }

  renderAbout() {
    return (
      <div className="container-fluid pt-5">
        <div className="card">
          <div className="card-body">
            <h3>Featured Funds</h3>
            <p>XYZ investment products are managed to be style-pure, to fulfill specific roles in your clients'
              asset allocation strategies.</p>
            <NavLink exact className="btn btn-outline-primary" to="/">
              View our Featured Funds
              <i className="fas fa-lg fa-angle-right ml-2" aria-hidden="true" />
            </NavLink>
          </div>
        </div>
      </div>
    );
  }
}

export default AboutPage;
