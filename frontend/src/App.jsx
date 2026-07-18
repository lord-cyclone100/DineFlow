import { useContext } from 'react'
import { AuthPage } from './pages/AuthPage'
import { HomePage } from './pages/HomePage'
import { AuthContext } from './auth/AuthContext'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { AppLayout } from './components/layout/AppLayout'
import { ContactPage } from './pages/ContactPage'
import { AboutPage } from './pages/AboutPage'
import { ProtectedRoute } from './auth/ProtectedRoute'
import { ProfilePage } from './pages/ProfilePage'
import { OAuth2Redirect } from './pages/OAuth2Redirect'
import { AdminPanel } from './pages/AdminPanel'
import { ParticularUserSection } from './components/ui/ParticularUserSection'
import { ParticularRestaurant } from './components/ui/ParticularRestaurant'

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
          path:'/oauth2/redirect',
          element:<OAuth2Redirect />
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
            },
            {
              path:'/profile',
              element:<ProfilePage />
            },
            {
              path:'/admin',
              element:<AdminPanel />
            },
            {
              path:'/admin/users/:userId',
              element:<ParticularUserSection />
            },
            {
              path:'/restaurants/:restaurantId',
              element:<ParticularRestaurant />
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
