import { Footer, Header, SideMenu, TopBanner } from '@/widgets';
import Intro from '@/pages/home/ui/Intro.tsx';

const Home = () => {
    return (
        <>
            <TopBanner />
            <Header />
            <article>
                <Intro />
            </article>
            <SideMenu />
            <Footer />
        </>
    );
};

export default Home;
