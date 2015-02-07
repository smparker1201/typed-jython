# -*- coding: utf-8 -*-

"""Misc os module tests

Made for Jython.
"""
import array
import glob
import os
import subprocess
import sys
import unittest
from test import test_support
from java.io import File


class OSFileTestCase(unittest.TestCase):

    def setUp(self):
        open(test_support.TESTFN, 'w').close()

    def tearDown(self):
        if os.path.exists(test_support.TESTFN):
            os.remove(test_support.TESTFN)

    def test_issue1727(self):
        os.stat(*(test_support.TESTFN,))

    def test_issue1755(self):
        os.remove(test_support.TESTFN)
        self.assertRaises(OSError, os.utime, test_support.TESTFN, None)

    @unittest.skipUnless(hasattr(os, 'link'), "os.link not available")
    def test_issue1824(self):
        os.remove(test_support.TESTFN)
        self.assertRaises(OSError, os.link,
                          test_support.TESTFN, test_support.TESTFN)

    def test_issue1825(self):
        os.remove(test_support.TESTFN)
        testfnu = unicode(test_support.TESTFN)
        try:
            os.open(testfnu, os.O_RDONLY)
        except OSError, e:
            self.assertTrue(isinstance(e.filename, unicode))
            self.assertEqual(e.filename, testfnu)
        else:
            self.assertTrue(False)

        # XXX: currently fail
        #for fn in os.chdir, os.listdir, os.rmdir:
        for fn in (os.rmdir,):
            try:
                fn(testfnu)
            except OSError, e:
                self.assertTrue(isinstance(e.filename, unicode))
                self.assertEqual(e.filename, testfnu)
            else:
                self.assertTrue(False)


class OSDirTestCase(unittest.TestCase):

    def setUp(self):
        self.base = test_support.TESTFN
        self.path = os.path.join(self.base, 'dir1', 'dir2', 'dir3')
        os.makedirs(self.path)

    def test_rmdir(self):
        # Remove end directory
        os.rmdir(self.path)
        # Fail to remove a chain of directories
        self.assertRaises(OSError, os.rmdir, self.base)

    def test_issue2083(self):
        # Should fail to remove/unlink directory
        self.assertRaises(OSError, os.remove, self.path)
        self.assertRaises(OSError, os.unlink, self.path)

    def tearDown(self):
        # Some dirs may have been deleted. Find the longest that exists.
        p = self.path
        while not os.path.exists(p) and p != self.base:
            p = os.path.dirname(p)
        os.removedirs(p)


class OSStatTestCase(unittest.TestCase):

    def setUp(self):
        open(test_support.TESTFN, 'w').close()

    def tearDown(self):
        if os.path.exists(test_support.TESTFN):
            os.remove(test_support.TESTFN)

    def test_stat_with_trailing_slash(self):
        self.assertRaises(OSError, os.stat, test_support.TESTFN + os.path.sep)
        self.assertRaises(OSError, os.lstat, test_support.TESTFN + os.path.sep)


class OSWriteTestCase(unittest.TestCase):

    def setUp(self):
        self.fd = os.open(test_support.TESTFN, os.O_WRONLY | os.O_CREAT)

    def tearDown(self):
        if self.fd :
            os.close(self.fd)
            if os.path.exists(test_support.TESTFN):
                os.remove(test_support.TESTFN)

    def do_write(self, b, nx=None):
        if nx is None : nx = len(b)
        n = os.write(self.fd, b)
        self.assertEqual(n, nx, "os.write length error: " + repr(b))

    def test_write_buffer(self): # Issue 2062
        s = b"Big Red Book"
        for type2test in (str, buffer, bytearray, (lambda x : array.array('b',x))) :
            self.do_write(type2test(s))

        with memoryview(s) as m :
            self.do_write(m)
            # not contiguous:
            self.assertRaises(BufferError, self.do_write, m[1::2])

        # lacks buffer api:
        self.assertRaises(TypeError, self.do_write, 1.5, 4)

class UnicodeTestCase(unittest.TestCase):

    def test_env(self):
        with test_support.temp_cwd(name=u"tempcwd-中文"):
            newenv = os.environ.copy()
            newenv["TEST_HOME"] = u"首页"
            p = subprocess.Popen([sys.executable, "-c",
                                  'import sys,os;' \
                                  'sys.stdout.write(os.getenv("TEST_HOME").encode("utf-8"))'],
                                 stdout=subprocess.PIPE,
                                 env=newenv)
            self.assertEqual(p.stdout.read().decode("utf-8"), u"首页")
    
    def test_getcwd(self):
        with test_support.temp_cwd(name=u"tempcwd-中文") as temp_cwd:
            p = subprocess.Popen([sys.executable, "-c",
                                  'import sys,os;' \
                                  'sys.stdout.write(os.getcwd().encode("utf-8"))'],
                                 stdout=subprocess.PIPE)
            self.assertEqual(p.stdout.read().decode("utf-8"), temp_cwd)

    def test_listdir(self):
        # It is hard to avoid Unicode paths on systems like OS X. Use
        # relative paths from a temp CWD to work around this
        with test_support.temp_cwd() as new_cwd:
            unicode_path = os.path.join(".", "unicode")
            self.assertIs(type(unicode_path), str)
            chinese_path = os.path.join(unicode_path, u"中文")
            self.assertIs(type(chinese_path), unicode)
            home_path = os.path.join(chinese_path, u"首页")
            os.makedirs(home_path)
            
            with open(os.path.join(home_path, "test.txt"), "w") as test_file:
                test_file.write("42\n")

            # Verify works with str paths, returning Unicode as necessary
            entries = os.listdir(unicode_path)
            self.assertIn(u"中文", entries)

            # Verify works with Unicode paths
            entries = os.listdir(chinese_path)
            self.assertIn(u"首页", entries)
           
            # glob.glob builds on os.listdir; note that we don't use
            # Unicode paths in the arg to glob
            self.assertEqual(
                glob.glob(os.path.join("unicode", "*")),
                [os.path.join(u"unicode", u"中文")])
            self.assertEqual(
                glob.glob(os.path.join("unicode", "*", "*")),
                [os.path.join(u"unicode", u"中文", u"首页")])
            self.assertEqual(
                glob.glob(os.path.join("unicode", "*", "*", "*")),
                [os.path.join(u"unicode", u"中文", u"首页", "test.txt")])

            # Now use a Unicode path as well as in the glob arg
            self.assertEqual(
                glob.glob(os.path.join(u"unicode", "*")),
                [os.path.join(u"unicode", u"中文")])
            self.assertEqual(
                glob.glob(os.path.join(u"unicode", "*", "*")),
                [os.path.join(u"unicode", u"中文", u"首页")])
            self.assertEqual(
                glob.glob(os.path.join(u"unicode", "*", "*", "*")),
                [os.path.join(u"unicode", u"中文", u"首页", "test.txt")])
 
            # Verify Java integration. But we will need to construct
            # an absolute path since chdir doesn't work with Java
            # (except for subprocesses, like below in test_env)
            for entry in entries:
                entry_path = os.path.join(new_cwd, chinese_path, entry)
                f = File(entry_path)
                self.assertTrue(f.exists(), "File %r (%r) should be testable for existence" % (
                    f, entry_path))

class LocaleTestCase(unittest.TestCase):

    def get_installed_locales(self, codes, msg=None):
        def normalize(code):
            # OS X and Ubuntu (at the very least) differ slightly in locale code formatting
            return code.strip().replace("-", "").lower()

        try:
            installed_codes = dict(((normalize(code), code) for 
                                    code in subprocess.check_output(["locale", "-a"]).split()))
        except (subprocess.CalledProcessError, OSError):
            raise unittest.SkipTest("locale command not available, cannot test")

        if msg is None:
            msg = "One of %s tested locales is not installed" % (codes,)
        available_codes = []
        for code in codes:
            if normalize(code) in installed_codes:
                available_codes.append(installed_codes[normalize(code)])
        unittest.skipUnless(available_codes, msg)
        return available_codes

    # must be on posix and turkish locale supported
    def test_turkish_locale_posix_module(self):
        # Verifies fix of http://bugs.jython.org/issue1874
        self.get_installed_locales(["tr_TR.UTF-8"], "Turkish locale not installed, cannot test")
        newenv = os.environ.copy()
        newenv["LC_ALL"] = "tr_TR.UTF-8"  # set to Turkish locale
        self.assertEqual(
            subprocess.check_output(
                [sys.executable, "-c",
                 "import sys; assert 'posix' in sys.builtin_module_names"],
                env=newenv),
            "")

    def test_turkish_locale_string_lower_upper(self):
        # Verifies fix of http://bugs.jython.org/issue1874
        self.get_installed_locales(["tr_TR.UTF-8"], "Turkish locale not installed, cannot test")
        newenv = os.environ.copy()
        newenv["LC_ALL"] = "tr_TR.UTF-8"  # set to Turkish locale
        self.assertIn(
            subprocess.check_output(
                [sys.executable, "-c",
                 'print repr(["I".lower(), u"I".lower(), "i".upper(), u"i".upper()])'],
                env=newenv),
            # Should not convert str for 'i'/'I', but should convert
            # unicode if in Turkish locale; this behavior intentionally is
            # different than CPython; see also http://bugs.python.org/issue17252
            # 
            # Note that JVMs seem to have some latitude here however, so support
            # either for now.
            ["['i', u'\\u0131', 'I', u'\\u0130']\n",
             "['i', u'i', 'I', u'I']\n"])

    def test_strptime_locale(self):
        # Verifies fix of http://bugs.jython.org/issue2261
        newenv = os.environ.copy()
        codes = [
            "cs_CZ.UTF-8", "pl_PL.UTF-8", "ru_RU.UTF-8",
            "sk_SK.UTF-8", "uk_UA.UTF-8", "zh_CN.UTF-8"]
        for code in self.get_installed_locales(codes):
            newenv["LC_ALL"] = code
            self.assertEqual(
                subprocess.check_output(
                    [sys.executable, "-c",
                     'import datetime; print(datetime.datetime.strptime("2015-01-22", "%Y-%m-%d"))'],
                    env=newenv),
                "2015-01-22 00:00:00\n")


class SystemTestCase(unittest.TestCase):

    def test_system_no_site_import(self):
        # If not importing site (-S), importing traceback previously
        # would fail with an import error due to creating a circular
        # import chain. This root cause is because the os module
        # imports the subprocess module for the system function; but
        # the subprocess module imports from os. Verrifies that this
        # managed by making the import late; also verify the
        # monkeypatching optimization is successful by calling
        # os.system twice.
        with test_support.temp_cwd() as temp_cwd:
            self.assertEqual(
                subprocess.check_output(
                    [sys.executable, "-S", "-c",
                     "import traceback; import os; os.system('echo 42'); os.system('echo 47')"])\
                .replace("\r", ""),  # in case of running on Windows
                "42\n47\n")


def test_main():
    test_support.run_unittest(
        OSFileTestCase, 
        OSDirTestCase,
        OSStatTestCase,
        OSWriteTestCase,
        UnicodeTestCase,
        LocaleTestCase,
        SystemTestCase
    )

if __name__ == '__main__':
    test_main()
