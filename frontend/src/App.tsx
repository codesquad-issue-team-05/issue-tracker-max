import { useContext, useState } from 'react';
import { ThemeProvider } from 'styled-components';
import { lightTheme } from './theme';
import { darkTheme } from './theme';
import GlobalStyle from './style/Global';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Components from './pages/Components';
import Login from './pages/Login';
import Register from './pages/Register';

import LogoDarkLarge from './asset/logo/logo_dark_large.svg';
import LogoDarkMedium from './asset/logo/logo_dark_medium.svg';
import LogoLightLarge from './asset/logo/logo_light_large.svg';
import LogoLightMedium from './asset/logo/logo_light_medium.svg';
import { AppContext } from './main';
import Authenticate from './routes/Authenticate';

function App() {
  const [isLight, setIsLight] = useState<boolean>(true);
  const [isAuthenticated, setIsAuthenticated] = useState(true);
  const { util, control } = useContext(AppContext);

  Object.assign(util, {
    getLogoByTheme: () =>
      isLight
        ? {
            large: LogoLightLarge,
            medium: LogoLightMedium,
          }
        : {
            large: LogoDarkLarge,
            medium: LogoDarkMedium,
          },
    isLogin: () => isAuthenticated,
  });

  Object.assign(control, {
    changeTheme: () => {
      setIsLight(!isLight);
    },
    loginCheck: () => setIsAuthenticated(true),
    logoutCheck: () => setIsAuthenticated(false),
  });

  return (
    <ThemeProvider theme={isLight ? lightTheme : darkTheme}>
      <GlobalStyle />
      {/* <ButtonSmall type="button" ghost flexible onClick={changeTheme}>
        Change Theme
      </ButtonSmall> */}
      <Router>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/component" element={<Components />} />
          <Route path="/*" element={<Authenticate />} />
        </Routes>
      </Router>
    </ThemeProvider>
  );
}

export default App;
