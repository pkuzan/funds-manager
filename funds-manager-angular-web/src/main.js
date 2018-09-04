import ReactDOM from 'react-dom';
import React from 'react';
import 'whatwg-fetch';
import {BrowserRouter} from 'react-router-dom';
import {AppplicationContainer} from 'react-hot-loader';
import App from './components/App';
import 'styles/app.css';

const render = (Component) => {
    ReactDOM.render(
        <AppContainer>
            <BrowserRouter>
                <Component />
            </BrowserRouter>
        </AppContainer>,
        document.getElementById('app')
    );
};

render(App);

if(module.hot){
    module.hot.accept('./components/App', () => {
        const NextApp = require('./components/App').default;
        render(NextApp);
    });
}
