import axios from '../api/axios';
import useAuth from './useAuth';
import { AuthUser } from '../context/AuthProvider';

const useRefreshToken = () => {
  const { setAuth } = useAuth();

  const refresh = async () => {
    const response = await axios.post(
      '/api/reissue/token',
      JSON.stringify({ refreshToken: localStorage.getItem('refreshToken') }),
      {
        headers: { 'Content-Type': 'application/json' },
        withCredentials: true,
      }
    );
    setAuth((prev) => {
      // 이전 상태가 null일 경우
      if (!prev) {
        return null;
      }

      const newAuth: AuthUser = {
        userId: prev.userId,
        userName: prev.userName,
        profileImg: prev.profileImg,
        accessToken: response.data.message.accessToken,
      };
      return newAuth;
    });
    localStorage.setItem('accessToken', response.data.message.accessToken);

    return response.data.accessToken;
  };
  return refresh;
};

export default useRefreshToken;
