import type { ReactNode } from 'react';
import { BrowserRouter } from 'react-router-dom';

// Add your providers here (e.g., React Query, Router, Theme, etc.)
// Example:
// import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
// import { BrowserRouter } from 'react-router-dom';

interface ProvidersProps {
    children: ReactNode;
}

export function Providers({ children }: ProvidersProps) {
    return <BrowserRouter>{children}</BrowserRouter>;
}
