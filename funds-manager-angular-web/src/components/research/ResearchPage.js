import React from 'react';
import {Route, Redirect, Switch} from 'react-router';
import StockResearchPage from './stocks/StockResearchPage';
import {navlink} from 'react-router-dom';

export default class ResearchPage extends React.Component {
    render(){
        const {match} = this.props;
        return (
            <div>
                <nav className="side-menu">
                    <ul className="list-group list-group-nav">
                        <li>
                            <NavLink classNme="list-group-item" to={`${match.url}`}>Stock Research></NavLink>
                        </li>
                    </ul>
                </nav>
                <main>
                    <Switch>
                        <Route path={`${match.url}/stocks`} component={StockResearchPage}/>
                        <Redirect from={`${match.url}`} to={`${match.url}/stocks`}/>
                    </Switch>
                </main>
            </div>
        );
    }
}