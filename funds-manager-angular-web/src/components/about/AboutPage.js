import React from 'react';
import {NavLink} from 'react-router-dom';

class AboutPage extends React.Component {
    render(){
        return {
            <div>
                {this.renderAbout()}
            </div>
        }
    }

    renderAbout(){
        <p>About</p>
    }

}

export default AboutPage;