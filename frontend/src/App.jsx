import { useContext } from 'react'
import { AuthPage } from './pages/AuthPage'
import { HomePage } from './pages/HomePage'
import { AuthContext } from './auth/AuthContext'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { AppLayout } from './components/layout/AppLayout'
import { ContactPage } from './pages/ContactPage'
import { AboutPage } from './pages/AboutPage'
import { ProtectedRoute } from './auth/ProtectedRoute'

export const App = () => {

  const { isAuthenticated } = useContext(AuthContext)

  const token = localStorage.getItem('accessToken')
  console.log(token);
  const router = createBrowserRouter([
    {
      path:'/',
      element:<AppLayout />,
      children:[
        {
          path:'/login',
          element:<AuthPage />
        },
        {
          element:<ProtectedRoute />,
          children:[
            {
              path:'/',
              element:<HomePage />
            },
            {
              path:'/contact',
              element:<ContactPage />
            },
            {
              path:'/about',
              element:<AboutPage />
            }
          ]
        },
      ]
    }
  ])
  return (
    <>
      {/* {isAuthenticated ? <HomePage /> : <AuthPage />} */}
      <RouterProvider router={router} />
    </>
  )
}
