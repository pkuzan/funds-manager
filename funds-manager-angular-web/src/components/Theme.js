import Cookie from 'js-cookie';

export default class Theme {
    static getTheme(){
        let preferenceTheme;
        const themeCookie = Cookie.get('theme');
        if(themeCookie && (themeCookie === 'light' || themeCookie === 'dark')){
            preferenceTheme = themeCookie;
        }

        const documentTheme = document.documentElement.className;
        let theme;
        if (preferenceTheme){
            theme = preferenceTheme;
            if(preferenceTheme !== documentTheme){
                this.setTheme(theme);
            }
        } else {
            theme = documentTheme;
        }
        return theme;
    }

    static setTheme(theme){
        document.documentElement.className = theme;
        Cookie.set('theme', theme, {expires: 365})
    }
}