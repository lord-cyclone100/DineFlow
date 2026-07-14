import { AuthPage } from './pages/AuthPage'
import { HomePage } from './pages/HomePage'

export const App = () => {

  const token = localStorage.getItem('accessToken')
  console.log(token);
  return (
    <>
      {token ? <HomePage /> : <AuthPage />}
    </>
  )
}
