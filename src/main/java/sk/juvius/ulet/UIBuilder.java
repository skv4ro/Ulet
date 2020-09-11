package sk.juvius.ulet;

import com.ptc.cipjava.jxthrowable;
import com.ptc.pfc.pfcSession.Session;
import sk.juvius.ulet.cmds.connect.ShareManConnect;
import sk.juvius.ulet.cmds.download.DownloadAll;
import sk.juvius.ulet.cmds.download.DownloadSingle;
import sk.juvius.ulet.cmds.download.DownloadWhole;
import sk.juvius.ulet.cmds.download.Downloader;
import sk.juvius.ulet.cmds.upload.UploadSingle;
import sk.juvius.ulet.cmds.upload.UploadWhole;

public class UIBuilder extends AbstractBuilder {

    UIBuilder(Session session) {
        super(session);
    }

    @Override
    public void build() throws jxthrowable {
        buildCommand(
                new ShareManConnect(session),
                "u-let_connect.cmd",
                "u-let_connect.png",
                "u-let_connect.label",
                "u-let_connect.help",
                null);

        buildCommand(
                new DownloadAll(session),
                "u-let_download.cmd",
                "u-let_download.png",
                "u-let_download.label",
                "u-let_download.help",
                null);

        buildCommand(
                new UploadSingle(session),
                "u-let_upload_single.cmd",
                "u-let_upload_single.png",
                "u-let_upload_single.label",
                "u-let_upload_single.help",
                null);
        buildCommand(
                new UploadWhole(session),
                "u-let_upload_whole.cmd",
                "u-let_upload_whole.png",
                "u-let_upload_whole.label",
                "u-let_upload_whole.help",
                null);

        buildCommand(
                new DownloadSingle(session),
                "u-let_download_single.cmd",
                "u-let_download_single.png",
                "u-let_download_single.label",
                "u-let_download_single.help",
                null);

        buildCommand(
                new DownloadWhole(session),
                "u-let_download_whole.cmd",
                "u-let_download_whole.png",
                "u-let_download_whole.label",
                "u-let_download_whole.help",
                null);

        buildCommand(
                new Downloader(session, "xxx"),
                Downloader.CMD_NAME,
                null,
                "u-let_downloader.label",
                "u-let_downloader.help",
                null);
    }
}
