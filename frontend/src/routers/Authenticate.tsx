import { AppContext } from '../main';
import { useContext } from 'react';
import Main from '../pages/Main';
import { Navigate, Route, Routes } from 'react-router-dom';

export default function Authenticate() {
  const { util } = useContext(AppContext);

  return util.isLogin() ? (
    <Routes>
      <Route path="/" element={<Main />} />
    </Routes>
  ) : (
    <Navigate to="/login" replace />
  );
}
