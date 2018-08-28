import BaseApi from './BaseApi';

class FundApi extends BaseApi{
    getAssetClasses(){
        return this._get('/api/fund/asset-class').then(this._json);
    }

    getFunds(){
        return this._get('/api/fund').then(this._json);
    }

    deleteFund(symbol){
        return this._delete(`/api/fund/${symbol}`);
    }

    getFund(symbol){
        return this._get(`/api/fund/${symbol}`).then(this._json);
    }

    addFund(fund){
        return this._post('/api/fund/', fund);
    }

    editFund(fund){
        return this._put('/api/fund/', fund);
    }

    getHoldingCompanies(){
        return this._get('/api/fund/holding').then(this._json);
    }

    saveHolding(fundSymbol, holding){
        return this._put(`api/fund/${fundSymbol}/holding`, holding);
    }

    deleteHolding(fundSymbol, holdingSymbol){
        return this._delete(`api/fund/${fundSymbol}/holding/${holdingSymbol}`);
    }

    addHolding(fundSymbol, holding){
        return this._post(`api/fund/${fundSymbol}/holding`, holding)
    }
}

export default new FundApi();