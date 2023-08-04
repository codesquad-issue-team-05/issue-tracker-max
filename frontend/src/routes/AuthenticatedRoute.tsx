import { useState, useContext } from 'react';
import { AppContext } from '../main';
import { Navigate, Route, Routes } from 'react-router-dom';
import Main from '../pages/Main';

export default function AuthenticatedRoute() {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(true);
  const { util, control } = useContext(AppContext);
  util.isLogin = () => isAuthenticated;
  control.loginCheck = () => setIsAuthenticated(true);
  control.logoutCheck = () => setIsAuthenticated(false);

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  return (
    <Routes>
      <Route path="/" element={<Main />} />
    </Routes>
  );
}
