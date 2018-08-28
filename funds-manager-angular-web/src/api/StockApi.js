import BaseApi from './BaseApi';

class StockApi extends BaseApi {
    getStockQuotes(symbols){
        return this._get(`api/stock/quotes/${symbols}`).then(this._json);
    }
}

export default new StockApi();